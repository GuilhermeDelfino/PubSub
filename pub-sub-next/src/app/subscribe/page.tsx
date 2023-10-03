"use client";
import { FormCreateNewSubscriber } from "@/components/templates/FormCreateNewSubscriber";
import { CreateNewSubscriber } from "@/core/application/useCases/CreateNewSubscriber";
import Subscriber from "@/core/domain/entities/Subscriber";
import {BffSiteGateway} from "@/core/infra/adapters/gateway/bff-site/BffSiteGateway";

const msSubscribersGateway = BffSiteGateway.getInstance();
const createNewSubscriberUseCase = new CreateNewSubscriber(
  msSubscribersGateway
);
const handleSubmitCreateNewSubscriber = ({ email, name }: Subscriber) => {
  createNewSubscriberUseCase.execute({
    email: email.value,
    name: name.value,
  });
};

export default function Home() {
  return (
    <main>
      <FormCreateNewSubscriber
        createNewSubscriber={handleSubmitCreateNewSubscriber}
      />
    </main>
  );
}
