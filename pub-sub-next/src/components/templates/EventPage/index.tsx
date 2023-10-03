import { Event } from "@/core/domain/entities/Event";
import { FC } from "react";
import styles from "./styles.module.css";
import { formatDate } from "@/util/date";
import { Container } from "@/components/atoms/Container";
import { Title } from "@/components/atoms";
import { Paragraph } from "@/components/atoms/Paragraph";
export const EventPageTemplate: FC<{ event: Event }> = ({
  event: { author, date, description, location, title },
}) => {
  const formatedDate = formatDate(date);
  return (
    <>
      <Container>
        <Title>{title}</Title>
        <Paragraph>{description}</Paragraph>
        <br />
        <Paragraph className={styles.author}>Author Name: {author}</Paragraph>
        <div className={styles.divider} />
        <div className={styles.date_and_location}>
          <small>Location: {location}</small>
          <small>Date: {formatedDate}</small>
        </div>
      </Container>
    </>
  );
};
