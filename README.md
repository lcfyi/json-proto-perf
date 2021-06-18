# Benchmarking Protobuf and JSON Schema Validation

This is a toy benchmark for the following data structure (as JSON for readability):
```json
{
  "id": 1,
  "email": "email@email",
  "name": "name",
  "location": {
    "lat": 10,
    "lng": 20
  }
}
```

The benchmark uses [ScalaPB validation](https://scalapb.github.io/docs/validation) for protobuf and [json-schema-validator](https://github.com/java-json-tools/json-schema-validator) for JSON.

## Results

Here are some numbers.

| Type     | Stream Length | Average (ns) | Max (ns) | Min (ns) | Diff (Avg, % over other) |
|----------|---------------|--------------|----------|----------|--------------------------|
| Protobuf | 1             | 274668       | 274668   | 274668   | 119.78%                  |
| Json     | 1             | 329010       | 329010   | 329010   | 83.48%                   |
| Protobuf | 10            | 28766        | 70681    | 7842     | 418.06%                  |
| Json     | 10            | 120260       | 295683   | 90446    | 23.92%                   |
| Protobuf | 100           | 10575        | 133849   | 7068     | 1496.98%                 |
| Json     | 100           | 158306       | 594405   | 88146    | 6.68%                    |
| Protobuf | 1000          | 9909         | 62626    | 6942     | 1316.87%                 |
| Json     | 1000          | 130489       | 447267   | 82221    | 7.59%                    |
| Protobuf | 10000         | 10012        | 145095   | 6631     | 940.29%                  |
| Json     | 10000         | 94142        | 1121034  | 50333    | 10.63%                   |

[1] The difference over the other is the other value's average divided by the current value's average

The numbers make sense -- ScalaPB's validator code-gen generates code that just chains a bunch of booleans to validate the data, while the json-schema-validator library has to walk the JsonNode object and validate (the schema does live as a walkable definition, so it shouldn't be too expensive after the initial build).

Something to note about the JSON Schema validation is that it short circuits on the first failed validation (unless the deep validation flag is set), so these numbers may differ depending on the data -- in aggregate, proto validation is still going to be much faster.

## TODO

- Benchmark using the same stream of data
- Benchmark using the simple values (it's going to be _much_ faster)
