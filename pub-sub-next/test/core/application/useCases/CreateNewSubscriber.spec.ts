import { CreateNewSubscriber } from "@/core/application/useCases/CreateNewSubscriber";
import { SubscriberGatewayMock } from "../../adapters/mock/SubscriberGatewayMock";
import Subscriber from "@/core/domain/entities/Subscriber";
import Name from "@/core/domain/VOs/Name";
import Email from "@/core/domain/VOs/Email";

const spyName = jest.spyOn(Name, "of");
const spyEmail = jest.spyOn(Email, "of");
jest.mock("../../adapters/mock/SubscriberGatewayMock");

describe("Use Case: Create New Subscriber", () => {
  it("should be create correcty with no errors", async () => {
    const mockGateway = new SubscriberGatewayMock();
    const useCase = new CreateNewSubscriber(mockGateway);

    await useCase.execute({
      email: "example@example.com",
      name: "example",
    });

    expect(mockGateway.createSubscriber).toHaveBeenCalledWith(
      expect.any(Subscriber)
    );
    expect(mockGateway.createSubscriber).toHaveBeenCalledTimes(1);

    expect(spyName).toHaveBeenCalledTimes(1);
    expect(spyName).toHaveBeenCalledWith(expect.any(String));

    expect(spyEmail).toHaveBeenCalledTimes(1);
    expect(spyEmail).toHaveBeenCalledWith(expect.any(String));
  });
});
