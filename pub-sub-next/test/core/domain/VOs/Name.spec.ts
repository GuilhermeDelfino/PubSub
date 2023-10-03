import Name from "@/core/domain/VOs/Name";
import EntityInvalid from "@/core/domain/errors/EntityValidation";

describe("Value Objects: Name", () => {
  it("should be create correctly a Name VO", () => {
    expect(Name.of("Example").value).toBe("Example");
    expect(Name.of("Example Correcly").value).toBe("Example Correcly");
    expect(Name.of("José Rafael").value).toBe("José Rafael");
  });

  it("should be create throw an Entity Validation Error when Name is less than 3 character", () => {
    expect(() => Name.of("ex")).toThrowError(EntityInvalid);
  });
  it("should be create throw an Entity Validation Error when Name contains number", () => {
    expect(() => Name.of("example12")).toThrowError(EntityInvalid);
  });
  it("should be create throw an Entity Validation Error when Name is greater than 50 characters", () => {
    expect(() => Name.of("e".repeat(51))).toThrowError(EntityInvalid);
  });
});
