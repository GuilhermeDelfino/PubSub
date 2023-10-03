import strings from "@/util/strings";
import { FC } from "react";
import { InputValidator } from "@/components/molecules/Input/validators";
import { InputFormatter } from "@/components/molecules/Input/formatters";
import { InputProps, Textfield } from "@/components/molecules";
import { LabelProps } from "@/components/atoms";

export type TextfieldDescriptionProps = {
  value: string;
  changeValue(value: string): void;
  validator: InputValidator;
};
export const TextfieldDescription: FC<TextfieldDescriptionProps> = ({
  changeValue,
  value,
  validator,
}) => {
  const { description: descriptionStrings } = strings.publishNewEvent.inputs;

  const input: InputProps = {
    type: "text",
    id: "ipt-description",
    inputMode: "text",
    minLength: 3,
    maxLength: 255,
    required: true,
    title: descriptionStrings.title,
    placeholder: descriptionStrings.placeholder,
    tabIndex: 2,
    changeValue,
    value,
    textarea: true,
    rows: 5,
    validator,
  };

  const labelName: LabelProps = {
    htmlFor: input.id,
    required: input.required,
    children: descriptionStrings.text,
  };
  return <Textfield input={input} label={labelName} />;
};
