import strings from "@/util/strings";
import { FC } from "react";
import styles from "./styles.module.css";
import { Title } from "@/components/atoms";

export const HeadingFormPublishNewEvent: FC = () => {
  const { title, description } = strings.publishNewEvent;

  return (
    <>
      <Title className={styles.title}>{title}</Title>
      <small className={styles.description}>{description}</small>
    </>
  );
};
