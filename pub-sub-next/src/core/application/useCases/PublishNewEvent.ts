import { EventsGateway } from "@/core/adapters/gateway/EventsGateway";
import { CreateEventDto } from "@/core/adapters/gateway/dtos/CreateEventDto";

export class PublishNewEvent {
  constructor(private readonly gateway: EventsGateway) {}

  async execute(ev: CreateEventDto) {
    return await this.gateway.createEvent(ev);
  }
}
