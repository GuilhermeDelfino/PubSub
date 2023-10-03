import { EventPageTemplate } from "@/components/templates/EventPage";
import { FindEventById } from "@/core/application/useCases/FindEventByID";
import { BffSiteGateway } from "@/core/infra/adapters/gateway/bff-site/BffSiteGateway";
const gateway = BffSiteGateway.getInstance();
const useCase = new FindEventById(gateway);
export default async function Event({ params }: { params: { id: string } }) {
  try {
    const event = await useCase.execute(params.id);
    if (event) {
      return (
        <main>
          <EventPageTemplate event={event} />
        </main>
      );
    }

    return (
      <main>
        <h1>Event not found</h1>
      </main>
    );
  } catch (error) {
    console.log({ error });
    return (
      <main>
        <h1>Find Event By Id Not Working</h1>
      </main>
    );
  }
}
