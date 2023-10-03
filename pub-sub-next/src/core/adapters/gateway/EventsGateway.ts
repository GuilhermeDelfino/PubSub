import { Event } from "@/core/domain/entities/Event";
import { CreateEventDto } from "./dtos/CreateEventDto";

export interface EventsGateway {
  findAllEvents(): Promise<Event[]>;
  findOneEvent(id: string): Promise<Event | null>;
  createEvent(event: CreateEventDto): Promise<Event>;
}
