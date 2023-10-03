import { DetailedHTMLProps, FC, HTMLAttributes } from "react";
export type ContainerProps = DetailedHTMLProps<
  HTMLAttributes<HTMLElement>,
  HTMLElement
> & {
  size?: "sm" | "md" | "lg";
};
import styles from "./styles.module.css";
export const Container: FC<ContainerProps> = ({ size = "md", ...props }) => {
  return (
    <section {...props} className={styles.container + " " + styles[size]} />
  );
};
