import strings from "@/util/strings";
import { FC } from "react";
import { InputValidator } from "@/components/molecules/Input/validators";
import { InputFormatter } from "@/components/molecules/Input/formatters";
import { InputProps, Textfield } from "@/components/molecules";
import { LabelProps } from "@/components/atoms";

export type TextfieldNameProps = {
  value: string;
  changeValue(value: string): void;
  validator: InputValidator;
  formatter: InputFormatter;
};
export const TextfieldName: FC<TextfieldNameProps> = ({
  changeValue,
  value,
  formatter,
  validator,
}) => {
  const { name: nameStrings } = strings.createSubscriber.inputs;

  const inputName: InputProps = {
    type: "text",
    id: "ipt-name",
    inputMode: "text",
    autoFocus: true,
    minLength: 3,
    maxLength: 50,
    required: true,
    title: nameStrings.title,
    placeholder: nameStrings.placeholder,
    tabIndex: 1,
    changeValue,
    value,
    validator,
    formatter,
  };

  const labelName: LabelProps = {
    htmlFor: inputName.id,
    required: inputName.required,
    children: nameStrings.text,
  };
  return <Textfield input={inputName} label={labelName} />;
};
