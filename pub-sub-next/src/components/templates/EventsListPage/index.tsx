import { EventCard } from "@/components/organisms/EventCard";
import { Event } from "@/core/domain/entities/Event";
import { FC } from "react";
import styles from "./styles.module.css";
import { formatDate } from "@/util/date";
import { Title } from "@/components/atoms";
import { Container } from "@/components/atoms/Container";

// TODO: Pagination
export type EventListProps = {
  events: Event[];
};
export const EventListPage: FC<EventListProps> = ({ events }) => {
  const createEvent = (event: Event) => (
    <EventCard event={event} key={event.id} />
  );
  const latestDateEvent = formatDate(events.at(-1)!.date);

  return (
    <Container size="md">
      <div className={styles.heading}>
        <Title>Recent Events Published: </Title>
        <small>Latest: {latestDateEvent}</small>
      </div>
      <section className={styles.section_card}>
        {events.map(createEvent)}
      </section>
    </Container>
  );
};
