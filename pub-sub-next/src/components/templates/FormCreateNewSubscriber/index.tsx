import { HeadingFormCreateNewSubscriber } from "./components/HeadingFormCreateNewSubscriber";
import Subscriber from "@/core/domain/entities/Subscriber";
import { FormRegisterSubscriber } from "./components/FormRegisterSubscriber";
import { Container } from "@/components/atoms/Container";

export function FormCreateNewSubscriber({
  createNewSubscriber,
}: {
  createNewSubscriber(subscriber: Subscriber): void;
}) {
  return (
    <Container size="sm">
      <HeadingFormCreateNewSubscriber />
      <FormRegisterSubscriber createNewSubscriber={createNewSubscriber} />
    </Container>
  );
}
