import { DetailedHTMLProps, FC, LabelHTMLAttributes } from "react";
import styles from "./styles.module.css";

export type LabelProps = {
  required?: boolean;
} & DetailedHTMLProps<LabelHTMLAttributes<HTMLLabelElement>, HTMLLabelElement>;
export const Label: FC<LabelProps> = (props) => {
  const classNameRequired = props.required ? styles.label_required : "";
  const classNameLabel =
    props.className || "" + " " + styles.label + " " + classNameRequired;
  return <label {...props} className={classNameLabel} />;
};
