import { EventsGateway } from "@/core/adapters/gateway/EventsGateway";

export class FindAllEvents {
  constructor(private readonly gateway: EventsGateway) {}
  execute() {
    return this.gateway.findAllEvents();
  }
}
