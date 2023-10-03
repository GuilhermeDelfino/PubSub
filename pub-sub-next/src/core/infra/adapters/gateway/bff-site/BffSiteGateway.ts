import { EventsGateway } from "@/core/adapters/gateway/EventsGateway";
import SubscriberGateway from "@/core/adapters/gateway/SubscriberGateway";
import { CreateEventDto } from "@/core/adapters/gateway/dtos/CreateEventDto";
import { Event } from "@/core/domain/entities/Event";
import Subscriber from "@/core/domain/entities/Subscriber";
import {
  MUTATION_CREATE_EVENT,
  MUTATION_CREATE_SUBSCRIBER,
  QUERY_FIND_ALL_EVENTS,
  QUERY_FIND_ONE_EVENT,
} from "./graphql/queries";
import {SubscriberMapper} from "../ms-subscriber/mappers/SubscriberMapper";
import {MsSubscriberMutationCreateNewSubscriber} from "../ms-subscriber/model/MsSubscriberMutationCreateNewSubscriber";
import {MsSubscriberResponse} from "../ms-subscriber/model/MsSubscriberResponse";
import {SubscriberModelMSSubscriberInput} from "../ms-subscriber/model/SubscriberModelMSSubscriberInput";

export class BffSiteGateway implements EventsGateway, SubscriberGateway {
  private static INSTANCE: null | BffSiteGateway = null;
  private endpointURL = process.env.BFF_HOST_URL || 'http://localhost:3332' + "/graphql";
  private constructor() {}
  static getInstance() {
    console.log("URL: ", process.env.BFF_HOST_URL);
    
    if (this.INSTANCE === null) this.INSTANCE = new BffSiteGateway();
    return this.INSTANCE;
  }

  async createSubscriber(model: Subscriber): Promise<void> {
    const subscriber: SubscriberModelMSSubscriberInput =
      SubscriberMapper.modelInputFromEntity(model);
      const body = JSON.stringify({
        query: MUTATION_CREATE_SUBSCRIBER,
        variables: { subscriber },
      });
  
      const requestOptions = {
        method: "post",
        headers: {
          "content-type": "application/json",
        },
        body,
      };
    const response = await fetch(this.endpointURL, requestOptions);

    const data =
      (await response.json()) as MsSubscriberResponse<MsSubscriberMutationCreateNewSubscriber>;

    console.log({ response, data });
  }
  async findAllEvents(): Promise<Event[]> {
    const response = await fetch(this.endpointURL, {
      body: JSON.stringify({
        query: QUERY_FIND_ALL_EVENTS,
      }),
      method: "post",
      headers: {
        "Content-Type": "application/json",
      },
      next: {
        revalidate: 60,
      },
    });
    if ([200, 201].includes(response.status)) {
      const data = await response.json();
      return data.data.findAllEvents as Event[];
    }

    throw new Error("BFF Site Gateway is not working.");
  }
  async findOneEvent(id: string): Promise<Event | null> {
    const response = await fetch(this.endpointURL, {
      body: JSON.stringify({
        query: QUERY_FIND_ONE_EVENT,
        variables: { id },
      }),
      method: "post",
      headers: {
        "Content-Type": "application/json",
      },
      next: {
        revalidate: 60 * 60,
      },
    });
    if ([200, 201].includes(response.status)) {
      const data = await response.json();
      if (data.errors) {
        throw new Error(data.errors[0].message);
      }

      return data.data.findOneEvent as Event;
    }

    throw new Error("BFF Site Gateway is not working.");
  }
  async createEvent(input: CreateEventDto): Promise<Event> {
    const response = await fetch(this.endpointURL, {
      body: JSON.stringify({
        query: MUTATION_CREATE_EVENT,
        variables: { input },
      }),
      method: "post",
      headers: {
        "Content-Type": "application/json",
      },
      cache: "no-cache",
    });
    if ([200, 201].includes(response.status)) {
      const data = await response.json();
      if (data.errors) {
        throw new Error(data.errors[0].message);
      }

      return data.data.createEvent as Event;
    }

    throw new Error("BFF Site Gateway is not working.");
  }
}
