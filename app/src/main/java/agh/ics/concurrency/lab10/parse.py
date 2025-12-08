text = open("moreconsumers.txt").read()


buffers = text.split("-")


def process_buffer(buffer):
    lines = buffer.strip().split("\n")
    numbers = [[int(x) for x in line.split(" ") if x] for line in lines if line]
    processed = [(sum(group)/len(group), (max(group) - min(group))/(sum(group)/len(group)) if sum(group) != 0 else 0) for group in numbers]
    return processed

processed_buffers = [process_buffer(buffer) for buffer in buffers]

print("Producers produce avgs")
for i, buffer in enumerate(processed_buffers):
    print(f"({i}, {buffer[0][0]:.2f})", end=" ")

print("\nProducers pass avgs")
for i, buffer in enumerate(processed_buffers):
    print(f"({i}, {buffer[2][0]:.2f})", end=" ")

print("\nConsumers consume avgs")
for i, buffer in enumerate(processed_buffers):
    print(f"({i}, {buffer[1][0]:.2f})", end=" ")

print("\nConsumers pass avgs")
for i, buffer in enumerate(processed_buffers):
    print(f"({i}, {buffer[3][0]:.2f})", end=" ")


print("\n\nProducers produce var")
for i, buffer in enumerate(processed_buffers):
    print(f"({i}, {buffer[0][1]:.2f})", end=" ")

print("\nProducers pass var")
for i, buffer in enumerate(processed_buffers):
    print(f"({i}, {buffer[2][1]:.2f})", end=" ")

print("\nConsumers consume var")
for i, buffer in enumerate(processed_buffers):
    print(f"({i}, {buffer[1][1]:.2f})", end=" ")

print("\nConsumers pass var")
for i, buffer in enumerate(processed_buffers):
    print(f"({i}, {buffer[3][1]:.2f})", end=" ")