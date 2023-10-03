import Email from "@/core/domain/VOs/Email";
import EntityInvalid from "@/core/domain/errors/EntityValidation";

describe("Value Objects: Email", () => {
  it("should be create correctly a Email VO", () => {
    expect(Email.of("example@example.com").value).toBe("example@example.com");
    expect(Email.of("example@gmail.com").value).toBe("example@gmail.com");
    expect(Email.of("example.example@example.example").value).toBe(
      "example.example@example.example"
    );
  });

  it("should be create throw an Entity Validation Error", () => {
    expect(() => Email.of("exampleexamplecom")).toThrowError(EntityInvalid);
    expect(() => Email.of("exampleexa@mplecom")).toThrowError(EntityInvalid);
    expect(() => Email.of("exampleexamp.lecom")).toThrowError(EntityInvalid);
    expect(() => Email.of("exampleexampl223.@ecom")).toThrowError(
      EntityInvalid
    );
  });
});
