import { Button } from "@/components/atoms";
import { render, screen } from "@testing-library/react";

const makeSut = () => {
  return render(
    <Button variant="primary" type="submit">
      Save
    </Button>
  );
};
describe("Components Atoms: Button", () => {
  it("should be render button with save text", () => {
    makeSut();

    const button = screen.getByRole("button", {
      name: /save/i,
    });

    expect(button).toBeInTheDocument();
    expect(button.getAttribute("type")).toBe("submit");
    expect(button.className.includes("button")).toBeTruthy();
    expect(button.className.includes("button_primary")).toBeTruthy();
  });
});
