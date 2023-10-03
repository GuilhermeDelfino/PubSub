import { Label, LabelProps } from "@/components/atoms/Label";
import { FC, HTMLAttributes } from "react";
import styles from "./styles.module.css";
import {Input, InputProps} from "../Input";

export type TextfieldProps = {
  label: LabelProps;
  input: InputProps;
} & HTMLAttributes<HTMLDivElement>;

export const Textfield: FC<TextfieldProps> = ({ label, input, ...props }) => {
  return (
    <div
      {...props}
      className={styles.textfield_container + " " + props.className}
    >
      <Label {...label} />
      <Input {...input} />
    </div>
  );
};
