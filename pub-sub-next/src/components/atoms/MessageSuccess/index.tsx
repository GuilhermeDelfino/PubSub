import { DetailedHTMLProps, FC, HTMLAttributes } from "react";
import styles from "./styles.module.css";
export type MessageSuccessProps = {} & DetailedHTMLProps<
  HTMLAttributes<HTMLElement>,
  HTMLElement
>;
export const MessageSuccess: FC<MessageSuccessProps> = (props) => {
  return (
    <small
      {...props}
      className={styles.message + " " + props.className || ""}
    />
  );
};
