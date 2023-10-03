import { ButtonHTMLAttributes, DetailedHTMLProps, FC } from "react";
import styles from "./styles.module.css";

const buttonStyles = {
  primary: styles.button_primary,
  secondary: styles.button_secondary,
};
export type ButtonVariant = "primary" | "secondary";
export type ButtonProps = {
  variant: ButtonVariant;
} & DetailedHTMLProps<
  ButtonHTMLAttributes<HTMLButtonElement>,
  HTMLButtonElement
>;
export const Button: FC<ButtonProps> = ({ variant, ...props }) => {
  const classNameButton =
    props.className || "" + " " + styles.button + " " + buttonStyles[variant];

  return <button {...props} className={classNameButton} />;
};
