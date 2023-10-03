import { Event } from 'src/events/models/event.model';
import { EventModel } from '../../models/event.model';

export class MsEventMapper {
  static fromResponse({
    authorName: author,
    date,
    ...data
  }: EventModel): Event {
    return {
      author,
      date: new Date(date),
      ...data,
    };
  }
}
