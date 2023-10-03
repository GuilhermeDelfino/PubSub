import {
  DetailedHTMLProps,
  FC,
  InputHTMLAttributes,
  TextareaHTMLAttributes,
} from "react";
import { ErrorList } from "./components/ErrorList";
import { InputValidator } from "./validators";
import { InputFormatter } from "./formatters";
import DatePicker, {
  ReactDatePickerProps,
  registerLocale,
} from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import ptBR from "date-fns/locale/pt-BR";
import { useChangeInput } from "./hooks/UseChangeInput";
import { defaultFormatDate, defaultFormatDateTime } from "@/util/date";

registerLocale("pt-BR", ptBR);

export type InputProps = {
  changeValue(value: string): void;
  textarea?: boolean;
  validator?: InputValidator;
  formatter?: InputFormatter;
  datePickerProps?: Omit<ReactDatePickerProps, "onChange">;
} & DetailedHTMLProps<InputHTMLAttributes<HTMLInputElement>, HTMLInputElement> &
  DetailedHTMLProps<
    TextareaHTMLAttributes<HTMLTextAreaElement>,
    HTMLTextAreaElement
  >;

export const Input: FC<InputProps> = ({
  changeValue,
  validator,
  formatter,
  textarea = false,
  type,
  datePickerProps,
  ...props
}) => {
  const {
    changeInput,
    changeInputDate,
    classNameInput,
    errors,
    date,
    setDate,
  } = useChangeInput({
    validator,
    formatter,
    changeValue,
    className: props.className || "",
  });

  if (type === "date") {
    return (
      <>
        <DatePicker
          {...datePickerProps}
          selected={date}
          dateFormat={defaultFormatDate}
          onChange={(date) => {
            changeInputDate(date);
            setDate(date!);
          }}
          className={classNameInput}
        />
        {errors.length > 0 && <ErrorList errors={errors} />}
      </>
    );
  }
  if (type === "datetime-local") {
    return (
      <>
        <DatePicker
          {...datePickerProps}
          selected={date}
          onChange={(date) => {
            changeInputDate(date);
            setDate(date!);
          }}
          showTimeSelect
          dateFormat={defaultFormatDateTime}
          className={classNameInput}
        />
        {errors.length > 0 && <ErrorList errors={errors} />}
      </>
    );
  }

  if (textarea) {
    return (
      <>
        <textarea
          {...props}
          className={classNameInput}
          onChange={changeInput}
        />
        {errors.length > 0 && <ErrorList errors={errors} />}
      </>
    );
  }
  return (
    <>
      <input
        {...props}
        type={type}
        className={classNameInput}
        onChange={changeInput}
      />
      {errors.length > 0 && <ErrorList errors={errors} />}
    </>
  );
};
