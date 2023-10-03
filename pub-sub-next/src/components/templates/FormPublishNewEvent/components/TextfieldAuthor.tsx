import strings from "@/util/strings";
import { FC } from "react";
import { InputValidator } from "@/components/molecules/Input/validators";
import { InputProps, Textfield } from "@/components/molecules";
import { LabelProps } from "@/components/atoms";

export type TextfieldAuthorProps = {
  value: string;
  changeValue(value: string): void;
  validator: InputValidator;
};

export const TextfieldAuthor: FC<TextfieldAuthorProps> = ({
  changeValue,
  value,
  validator,
}) => {
  const { authorName: authorStrings } = strings.publishNewEvent.inputs;
  const input: InputProps = {
    type: "string",
    id: "ipt-author-name",
    inputMode: "text",
    required: true,
    title: authorStrings.title,
    placeholder: authorStrings.placeholder,
    tabIndex: 3,
    value,
    minLength: 3,
    maxLength: 50,
    changeValue,
    validator: validator,
  };
  const labelEmail: LabelProps = {
    htmlFor: input.id,
    required: input.required,
    children: authorStrings.text,
  };
  return <Textfield input={input} label={labelEmail} />;
};
