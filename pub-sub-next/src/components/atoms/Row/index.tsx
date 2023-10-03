import { DetailedHTMLProps, FC, HTMLAttributes } from "react";
import styles from "./styles.module.css";

export type RowProps = DetailedHTMLProps<
  HTMLAttributes<HTMLDivElement>,
  HTMLDivElement
>;
export const Row: FC<RowProps> = (props) => {
  return (
    <div {...props} className={styles.row + " " + props.className || ""}></div>
  );
};
