import { Subscriber } from '../models/subscriber.model';

export abstract class SubscriberService {
  abstract findAll(): Promise<Subscriber[]>;
  abstract findOne(id: string): Promise<Subscriber | null>;
  abstract create(input: { name: string; email: string }): Promise<Subscriber>;
}
