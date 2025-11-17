# language: python
import re
import os
from collections import defaultdict, OrderedDict
import numpy as np
import matplotlib.pyplot as plt
import math

LOG_PATH = "/home/jangawronski/Documents/programming/java/ConcurrencyCourse/log.txt"

measure_headers = [
    ("Measure 2 Condition:", "2 Condition - buffer 40"),
    ("Measure 4 Condition:", "4 Condition - buffer 40"),
    ("Measure nested locks:", "Nested Locks - buffer 40"),
]

# parse file
with open(LOG_PATH, "r") as f:
    text = f.read()

# Split by headers and keep content
measures = OrderedDict()
for hdr, name in measure_headers:
    idx = text.find(hdr)
    if idx == -1:
        measures[name] = ""
    else:
        # find start of next header or end
        nxt_idx = len(text)
        for h2, _ in measure_headers:
            if h2 == hdr:
                continue
            pos = text.find(h2, idx + 1)
            if pos != -1 and pos > idx:
                nxt_idx = min(nxt_idx, pos)
        measures[name] = text[idx + len(hdr):nxt_idx].strip()

def parse_block(block):
    # returns seconds (sorted list) and dict label -> list(values aligned to seconds)
    lines = [l.strip() for l in block.splitlines() if l.strip()]
    sec_map = OrderedDict()
    label_values = defaultdict(dict)  # label -> {sec: value}
    sec_nums = []
    sec_re = re.compile(r'^(\d+)\.\s*(.*)$')
    pair_re = re.compile(r'([+-]\d+):\s*(\d+)')
    for line in lines:
        m = sec_re.match(line)
        if not m:
            continue
        sec = int(m.group(1))
        sec_nums.append(sec)
        rest = m.group(2)
        for p in pair_re.finditer(rest):
            label = p.group(1)  # like +1 or -20
            val = int(p.group(2))
            label_values[label][sec] = val
    if not sec_nums:
        return [], {}
    secs = sorted(set(sec_nums))
    # build arrays
    out = {}
    for label, d in label_values.items():
        arr = [np.nan] * len(secs)
        for i, s in enumerate(secs):
            if s in d:
                arr[i] = d[s]
            else:
                arr[i] = np.nan
        out[label] = np.array(arr, dtype=float)
    return secs, out

parsed = {}
for name, block in measures.items():
    secs, data = parse_block(block)
    parsed[name] = (secs, data)

# save each measure to its own file, legend under plot
def safe_name(s):
    return s.lower().replace(" ", "_").replace(":", "").replace("/", "_")

cmap = plt.get_cmap("tab20")
out_dir = os.path.dirname(LOG_PATH)

for name, (secs, data) in parsed.items():
    fname = os.path.join(out_dir, f"{safe_name(name)}.png")
    fig, ax = plt.subplots(figsize=(14, 8))  # wider and taller plot
    if not secs:
        ax.text(0.5, 0.5, f"No data for {name}", ha="center")
        ax.set_title(name)
        ax.set_ylabel("count")
    else:
        x = secs
        labels_sorted = sorted(data.keys(), key=lambda s: (s[0], int(s[1:])))
        for i, label in enumerate(labels_sorted):
            arr = data[label]
            color = cmap(i % 20)
            linestyle = "-" if label.startswith("+") else "--"
            ax.plot(x, arr, label=label, color=color, linestyle=linestyle, linewidth=1)
        ax.set_title(name)
        ax.set_ylabel("count")
        ax.grid(alpha=0.3)
        # place a figure-level legend under the plot and expand it to fill width
        handles, labels = ax.get_legend_handles_labels()
        fig.tight_layout()
        fig.subplots_adjust(bottom=0.10)  # make room for legend
        # bbox_to_anchor as (x0, y0, width, height) with mode='expand' fills horizontal space
        fig.legend(handles, labels,
                   loc='lower center',
                   bbox_to_anchor=(0, -0.02, 1, 0.18),
                   mode='expand',
                   ncol=max(1, math.ceil(len(labels) / 2)),
                   fontsize='small')
        ax.set_xlabel("seconds")
        fig.savefig(fname, dpi=150, bbox_inches="tight")
        print("Saved plot to", fname)
        plt.close(fig)