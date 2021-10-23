import csv
import json
import fastnumbers
from fastnumbers import isint
from fastnumbers import isfloat

def formatNumber(value):
    if isint(value):
        return int(value)
    elif isfloat(value):
        return float(value)
    else:
        return None

def formatProductsList(parts):
    subdict = []
    for part in parts:
        elements = part.split(":")
        key = elements[0].replace("\n", "").strip()
        value = formatNumber(elements[1])
        if value is not None:
            subdict.append({key: value})
        else:
            subdict.append({key: elements[1].strip()})
    return subdict

def formatProductsDict(parts):
    subdict = {}
    for part in parts:
        elements = part.split(":")
        key = elements[0].replace("\n", "").strip()
        value = formatNumber(elements[1])
        if value is not None:
            subdict[key] = value
        else:
            subdict[key] = elements[1].strip()
    return subdict


def check_rows(rows):
    for row in rows:
        for item in row.items():
            value = item[1]
            if len(value) == 0:
                row.pop(item[0], None)
                continue
            formatted = formatNumber(value)
            if formatted is not None:
                row[item[0]] = formatted
            elif value.find(":") != -1:
                parts = value.split(";")
                subdict = formatProductsDict(parts)
                row[item[0]] = subdict

def format_csv_to_json(from_file, to_file, to_file2):
  with open(from_file) as f:
      reader = csv.DictReader(f)
      rows = list(reader)

  check_rows(rows)

  with open(to_file, 'w') as f:
      json.dump(rows, f, indent=2)

  with open(to_file2, 'w') as f:
        json.dump(rows, f, indent=2)


format_csv_to_json('app/factories.csv', 'app/src/main/res/raw/factories.json', 'app/src/test/resources/testraw/factories.json')
format_csv_to_json('app/recepies.csv', 'app/src/main/res/raw/recepies.json', 'app/src/test/resources/testraw/recepies.json')

