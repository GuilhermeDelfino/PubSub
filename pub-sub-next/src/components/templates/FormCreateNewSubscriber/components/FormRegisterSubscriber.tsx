import { FC } from "react";
import { TextfieldName } from "./TextfieldName";
import { TextfieldEmail } from "./TextfieldEmail";
import { Button } from "@/components/atoms/Button";
import strings from "@/util/strings";
import Subscriber from "@/core/domain/entities/Subscriber";
import { useFormRegisterSubscriber } from "../hooks/UseFormRegisterSubscriber";
import { MessageSuccess } from "@/components/atoms/MessageSuccess";

export type FormRegisterSubscriberProps = {
  createNewSubscriber(subscriber: Subscriber): void;
};
export const FormRegisterSubscriber: FC<FormRegisterSubscriberProps> = ({
  createNewSubscriber,
}) => {
  const {
    buttons: {
      createButton: { text: buttonText },
    },
    response: { success: successText },
  } = strings.createSubscriber;

  const {
    email,
    inputEmailValidator,
    inputNameFormatter,
    inputNameValidator,
    isLoading,
    isSuccess,
    name,
    onSubmit,
    setEmail,
    setName,
  } = useFormRegisterSubscriber({ createNewSubscriber });

  return (
    <form onSubmit={onSubmit}>
      <br />
      {isSuccess && <MessageSuccess>{successText}</MessageSuccess>}
      <TextfieldName
        value={name}
        validator={inputNameValidator}
        formatter={inputNameFormatter}
        changeValue={(name) => setName(name)}
      />
      <TextfieldEmail
        value={email}
        validator={inputEmailValidator}
        changeValue={(email) => setEmail(email)}
      />
      <Button variant="primary" type="submit" disabled={isLoading}>
        {buttonText}
      </Button>
    </form>
  );
};
