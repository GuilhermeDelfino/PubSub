import SubscriberGateway from "@/core/adapters/gateway/SubscriberGateway";
import Subscriber from "@/core/domain/entities/Subscriber";

export class SubscriberGatewayMock implements SubscriberGateway {
  createSubscriber(subscriber: Subscriber): Promise<void> {
    return new Promise((resolve) => resolve());
  }
}
