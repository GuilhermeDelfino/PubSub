import { Event } from '../models/event.model';
import { CreateEventDto } from './models/create-event.dto';

export abstract class EventsService {
  abstract findAll(): Promise<Event[]>;
  abstract findOne(id: string): Promise<Event | null>;
  abstract create(input: CreateEventDto): Promise<Event>;
}
