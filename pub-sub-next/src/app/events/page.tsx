import { EventListPage } from "@/components/templates/EventsListPage";
import { FindAllEvents } from "@/core/application/useCases/FindAllEvents";
import { BffSiteGateway } from "@/core/infra/adapters/gateway/bff-site/BffSiteGateway";
const gateway = BffSiteGateway.getInstance();
const useCase = new FindAllEvents(gateway);
export default async function EventPage() {
  try {
    const events = await useCase.execute();

    if (events.length > 0) {
      return <EventListPage events={events} />;
    }
    return (
      <>
        <h1>We do not have some event published. Publish any now</h1>
      </>
    );
  } catch (error) {
    console.error(error);
    return (
      <>
        <h1>Fail to load Events. Came here late.</h1>
      </>
    );
  }
}
