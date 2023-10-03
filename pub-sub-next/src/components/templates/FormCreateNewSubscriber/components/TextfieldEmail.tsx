import strings from "@/util/strings";
import { FC } from "react";
import { InputValidator } from "@/components/molecules/Input/validators";
import { InputProps, Textfield } from "@/components/molecules";
import { LabelProps } from "@/components/atoms";

export type TextfieldEmailProps = {
  value: string;
  changeValue(value: string): void;
  validator: InputValidator;
};

export const TextfieldEmail: FC<TextfieldEmailProps> = ({
  changeValue,
  value,
  validator,
}) => {
  const { email: emailStrings } = strings.createSubscriber.inputs;
  const inputEmail: InputProps = {
    type: "email",
    id: "ipt-email",
    inputMode: "text",
    required: true,
    title: emailStrings.title,
    placeholder: emailStrings.placeholder,
    tabIndex: 2,
    value,
    changeValue,
    validator: validator,
  };
  const labelEmail: LabelProps = {
    htmlFor: inputEmail.id,
    required: inputEmail.required,
    children: emailStrings.text,
  };
  return <Textfield input={inputEmail} label={labelEmail} />;
};
