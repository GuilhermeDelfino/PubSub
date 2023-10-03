import { Event } from "@/core/domain/entities/Event";
import { FC } from "react";
import styles from "./styles.module.css";
import Link from "next/link";

export type EventCardType = {
  event: Event;
};
export const EventCard: FC<EventCardType> = ({ event }) => {
  const eventLink = "events/" + event.id;
  return (
    <div className={styles.card_container}>
      <h3 className={styles.card_title}>{event.title}</h3>
      <small className={styles.card_author}>{event.author}</small>
      <br />
      <small>Location: {event.location}</small>
      <div className={styles.card_divider}></div>
      <Link href={eventLink} className={styles.card_link}>
        See Details
      </Link>
    </div>
  );
};
