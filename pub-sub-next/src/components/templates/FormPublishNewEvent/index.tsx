import { HeadingFormPublishNewEvent } from "./components/HeadingFormPublishNewEvent";
import { CreateEventDto } from "@/core/adapters/gateway/dtos/CreateEventDto";
import { FormPublishNewEvent } from "./components/FormPublishNewEvent";
import { Event } from "@/core/domain/entities/Event";
import { Container } from "@/components/atoms/Container";

export function FormPublishNewEventPage({
  publishNewEvent,
}: {
  publishNewEvent(event: CreateEventDto): Promise<Event>;
}) {
  return (
    <Container size="sm">
      <HeadingFormPublishNewEvent />
      <FormPublishNewEvent publishEvent={publishNewEvent} />
    </Container>
  );
}
