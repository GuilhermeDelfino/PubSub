import Subscriber from "@/core/domain/entities/Subscriber";

export default interface SubscriberGateway {
  createSubscriber(subscriber: Subscriber): Promise<void>;
}
