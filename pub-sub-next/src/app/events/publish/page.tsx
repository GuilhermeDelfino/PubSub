"use client";
import { FormPublishNewEventPage } from "@/components/templates/FormPublishNewEvent";
import { CreateEventDto } from "@/core/adapters/gateway/dtos/CreateEventDto";
import { PublishNewEvent } from "@/core/application/useCases/PublishNewEvent";
import { BffSiteGateway } from "@/core/infra/adapters/gateway/bff-site/BffSiteGateway";

const gateway = BffSiteGateway.getInstance();
const useCase = new PublishNewEvent(gateway);

export default function Home() {
  const publishNewEvent = async (ev: CreateEventDto) =>
    await useCase.execute(ev);

  return (
    <main>
      <FormPublishNewEventPage publishNewEvent={publishNewEvent} />
    </main>
  );
}
