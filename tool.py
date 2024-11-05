a_list: list[str] = []
result: list[list[str]] = []
item: list[str] = []
line_n = 0

with open("ascii_art.txt") as f:
    for line in f:
        line_n += 1
        if line_n % 8 != 0:
            a_list.append(line.replace("\n", ""))

for i in range(len(a_list)):
    item.append(a_list[i])
    if i % 7 == 6:
        result.append(item)
        item = []

print(result)
for item in result:
    print(item)
    # for line in item:
    #     print(line)
    print()