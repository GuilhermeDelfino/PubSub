import SubscriberGateway from "@/core/adapters/gateway/SubscriberGateway";
import Email from "@/core/domain/VOs/Email";
import Name from "@/core/domain/VOs/Name";
import Subscriber from "@/core/domain/entities/Subscriber";

export type CreateNewSubscriberInput = {
  name: string;
  email: string;
};
export class CreateNewSubscriber {
  constructor(private readonly gateway: SubscriberGateway) {}

  execute({ email, name }: CreateNewSubscriberInput): Promise<void> {
    const subscriber = new Subscriber(Name.of(name), Email.of(email));
    return this.gateway.createSubscriber(subscriber);
  }
}
