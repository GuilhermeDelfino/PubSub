import strings from "@/util/strings";
import { FC } from "react";
import { InputValidator } from "@/components/molecules/Input/validators";
import { InputProps, Textfield } from "@/components/molecules";
import { LabelProps } from "@/components/atoms";

export type TextfieldTitleProps = {
  value: string;
  changeValue(value: string): void;
  validator: InputValidator;
};

export const TextfieldTitle: FC<TextfieldTitleProps> = ({
  changeValue,
  value,
  validator,
}) => {
  const { title: titleStrings } = strings.publishNewEvent.inputs;
  const input: InputProps = {
    type: "string",
    id: "ipt-title",
    inputMode: "text",
    autoFocus: true,
    required: true,
    title: titleStrings.title,
    placeholder: titleStrings.placeholder,
    tabIndex: 1,
    value,
    minLength: 3,
    maxLength: 50,
    changeValue,
    validator: validator,
  };
  const labelEmail: LabelProps = {
    htmlFor: input.id,
    required: input.required,
    children: titleStrings.text,
  };
  return <Textfield input={input} label={labelEmail} />;
};
