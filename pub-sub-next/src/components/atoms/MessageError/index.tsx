import { DetailedHTMLProps, FC, HTMLAttributes } from "react";
import styles from "./styles.module.css";
export type MessageErrorProps = {} & DetailedHTMLProps<
  HTMLAttributes<HTMLElement>,
  HTMLElement
>;
export const MessageError: FC<MessageErrorProps> = (props) => {
  return (
    <small
      {...props}
      className={styles.message + " " + props.className || ""}
    />
  );
};
