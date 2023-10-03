import SubscriberGateway from "@/core/adapters/gateway/SubscriberGateway";
import Subscriber from "@/core/domain/entities/Subscriber";
import { MUTATION_CREATE_SUBSCRIBER } from "./graphql/queries";
import { SubscriberMapper } from "./mappers/SubscriberMapper";
import { SubscriberModelMSSubscriberInput } from "./model/SubscriberModelMSSubscriberInput";
import { MsSubscriberResponse } from "./model/MsSubscriberResponse";
import { MsSubscriberMutationCreateNewSubscriber } from "./model/MsSubscriberMutationCreateNewSubscriber";

export default class MSSubscriberGateway implements SubscriberGateway {
  static INSTANCE: null | MSSubscriberGateway = null;
  private endpointURL = process.env.BFF_HOST_URL + "/graphql";
  private constructor() {}
  static getInstance() {
    if (this.INSTANCE === null) this.INSTANCE = new MSSubscriberGateway();
    return this.INSTANCE;
  }
  async createSubscriber(entity: Subscriber): Promise<void> {
    const subscriber: SubscriberModelMSSubscriberInput =
      SubscriberMapper.modelInputFromEntity(entity);
    const requestOptions = this.createRequestOptions(subscriber);
    const response = await fetch(this.endpointURL, requestOptions);

    const data =
      (await response.json()) as MsSubscriberResponse<MsSubscriberMutationCreateNewSubscriber>;

    console.log({ response, data });
  }

  private createRequestOptions(
    subscriber: SubscriberModelMSSubscriberInput
  ): RequestInit {
    const body = JSON.stringify({
      query: MUTATION_CREATE_SUBSCRIBER,
      variables: { subscriber },
    });

    return {
      method: "post",
      headers: {
        "content-type": "application/json",
      },
      body,
    };
  }
}
