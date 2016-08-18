def sort(l, m):
    for j in range(1, len(m)):
        for k in range(1, len(m)):
            if m[k] < m[k - 1]:
                tmp_m = m[k]
                m[k] = m[k - 1]
                m[k - 1] = tmp_m

                tmp_l = l[k]
                l[k] = l[k - 1]
                l[k - 1] = tmp_l
    return l, w


def knapsack(N, values, weights, max_weight):
    table = [[0 for x in range(max_weight + 1)] for y in range(N)]  # table[N][max_weight]

    sorted_values, sorted_weights = sort(values, weights)

    for i in range(N):
        for j in range(max_weight + 1):
            tangent_value = 0
            if sorted_weights[i] <= j:
                tangent_value = sorted_values[i] + table[i-1][j - sorted_weights[i]]

            table[i][j] = max(table[i - 1][j], tangent_value)

    print "Max value =", table[N - 1][max_weight]


v = [3, 7, 2, 9]
w = [2, 3, 4, 5]
knapsack(len(v), v, w, 5)
