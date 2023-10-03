import strings from "@/util/strings";
import { FC } from "react";
import { InputValidator } from "@/components/molecules/Input/validators";
import { InputProps, Textfield } from "@/components/molecules";
import { LabelProps } from "@/components/atoms";

export type TextfieldLocationProps = {
  value: string;
  changeValue(value: string): void;
  validator: InputValidator;
};

export const TextfieldLocation: FC<TextfieldLocationProps> = ({
  changeValue,
  value,
  validator,
}) => {
  const { location: locationStrings } = strings.publishNewEvent.inputs;
  const input: InputProps = {
    type: "string",
    id: "ipt-location",
    inputMode: "text",
    required: true,
    title: locationStrings.title,
    placeholder: locationStrings.placeholder,
    tabIndex: 4,
    value,
    minLength: 3,
    maxLength: 60,
    changeValue,
    validator: validator,
  };
  const labelEmail: LabelProps = {
    htmlFor: input.id,
    required: input.required,
    children: locationStrings.text,
  };
  return <Textfield input={input} label={labelEmail} />;
};
