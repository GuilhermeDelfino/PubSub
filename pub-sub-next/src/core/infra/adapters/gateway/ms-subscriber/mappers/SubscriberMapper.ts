import Subscriber from "@/core/domain/entities/Subscriber";
import { SubscriberModelMSSubscriberInput } from "../model/SubscriberModelMSSubscriberInput";

export class SubscriberMapper {
  static modelInputFromEntity({
    email: { value: email },
    name: { value: name },
  }: Subscriber): SubscriberModelMSSubscriberInput {
    return {
      email,
      name,
    };
  }
}
