import strings from "@/util/strings";
import { FC } from "react";
import { InputValidator } from "@/components/molecules/Input/validators";
import { InputProps, Textfield } from "@/components/molecules";
import { LabelProps } from "@/components/atoms";

export type TextfieldDateProps = {
  value: string;
  changeValue(value: string): void;
  validator: InputValidator;
};

export const TextfieldDate: FC<TextfieldDateProps> = ({
  changeValue,
  value,
  validator,
}) => {
  const { date: dateStrings } = strings.publishNewEvent.inputs;
  const input: InputProps = {
    type: "datetime-local",
    id: "ipt-date",
    inputMode: "numeric",
    autoFocus: true,
    required: true,
    title: dateStrings.title,
    placeholder: dateStrings.placeholder,
    tabIndex: 5,
    value,
    changeValue,
    validator: validator,
    datePickerProps: {
      minDate: new Date(),
    },
  };
  const labelEmail: LabelProps = {
    htmlFor: input.id,
    required: input.required,
    children: dateStrings.text,
  };
  return <Textfield input={input} label={labelEmail} />;
};
