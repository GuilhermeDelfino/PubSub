import { FC } from "react";
import { TextfieldTitle } from "./TextfieldTitle";
import { Button } from "@/components/atoms/Button";
import strings from "@/util/strings";
import { UsePublishEventForm } from "../hooks/UseForm";
import { CreateEventDto } from "@/core/adapters/gateway/dtos/CreateEventDto";
import { TextfieldDescription } from "./TextfieldDescription";
import { TextfieldAuthor } from "./TextfieldAuthor";
import { TextfieldLocation } from "./TextfieldLocation";
import { TextfieldDate } from "./TextfieldDate";
import { Event } from "@/core/domain/entities/Event";
import { MessageSuccess } from "@/components/atoms/MessageSuccess";
import { MessageError } from "@/components/atoms/MessageError";

export type FormPublishNewEvent = {
  publishEvent(dto: CreateEventDto): Promise<Event>;
};
export const FormPublishNewEvent: FC<FormPublishNewEvent> = ({
  publishEvent,
}) => {
  const {
    buttons: {
      createButton: { text: buttonText },
    },
    response: { success: successText },
  } = strings.publishNewEvent;

  const {
    inputValidatorDescription,
    inputValidatorEventDate,
    inputValidatorDefault,
    createdEvent,
    generalError,
    description,
    isLoading,
    isSuccess,
    location,
    author,
    title,
    date,
    setDescription,
    setLocation,
    setAuthor,
    onSubmit,
    setTitle,
    setDate,
  } = UsePublishEventForm({ publishEvent });

  const getSuccessMessage = () => successText.replace("{id}", createdEvent!.id);

  return (
    <form onSubmit={onSubmit}>
      <br />
      {isSuccess && <MessageSuccess>{getSuccessMessage()}</MessageSuccess>}
      {generalError && <MessageError>{generalError}</MessageError>}

      <TextfieldTitle
        value={title}
        validator={inputValidatorDefault}
        changeValue={(val) => setTitle(val)}
      />
      <TextfieldDescription
        value={description}
        validator={inputValidatorDescription}
        changeValue={(val) => setDescription(val)}
      />
      <TextfieldAuthor
        value={author}
        validator={inputValidatorDefault}
        changeValue={(val) => setAuthor(val)}
      />
      <TextfieldLocation
        value={location}
        validator={inputValidatorDefault}
        changeValue={(val) => setLocation(val)}
      />
      <TextfieldDate
        value={date}
        validator={inputValidatorEventDate}
        changeValue={(val) => setDate(val)}
      />
      <Button variant="primary" type="submit" disabled={isLoading}>
        {buttonText}
      </Button>
    </form>
  );
};
