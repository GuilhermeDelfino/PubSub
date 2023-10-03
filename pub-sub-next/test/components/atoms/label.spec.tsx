import { Button, Label } from "@/components/atoms";
import { fireEvent, render, screen, waitFor } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
const makeSut = () => {
  return render(
    <>
      <input type="text" id="fake-id" placeholder="fake" />
      <Label htmlFor="fake-id">Click me!</Label>
    </>
  );
};
describe("Components Atoms: Label", () => {
  it("should be render Label with Click me! text", () => {
    makeSut();

    const label = screen.getByText(/click me/i);

    expect(label).toBeInTheDocument();
    expect(label.className.includes("label")).toBeTruthy();
  });

  it("when I click in label, it should be focus in input", async () => {
    makeSut();

    const user = userEvent.setup();

    const label = screen.getByText(/click me/i);
    const input = screen.getByPlaceholderText(/fake/i);

    await user.click(label);

    expect(input).toHaveFocus();
  });
});
