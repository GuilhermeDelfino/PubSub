import { BaseResponseMsSubscribers } from './base.response';
import { SubscriberModelMSSubscriber } from './subscriber.model';

export type FindAllData = {
  findAll: SubscriberModelMSSubscriber[];
};
export type FindAllResponseMsSubscribers =
  BaseResponseMsSubscribers<FindAllData>;
