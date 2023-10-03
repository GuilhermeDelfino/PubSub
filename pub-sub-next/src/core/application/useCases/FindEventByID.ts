import { EventsGateway } from "@/core/adapters/gateway/EventsGateway";

export class FindEventById {
  constructor(private readonly gateway: EventsGateway) {}
  execute(id: string) {
    return this.gateway.findOneEvent(id);
  }
}
