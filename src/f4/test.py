import sympy


# https://ru.wikipedia.org/wiki/%D0%A0%D0%B5%D1%88%D0%B5%D1%82%D0%BE_%D0%AD%D1%80%D0%B0%D1%82%D0%BE%D1%81%D1%84%D0%B5%D0%BD%D0%B0#/media/%D0%A4%D0%B0%D0%B9%D0%BB:New_Animation_Sieve_of_Eratosthenes.gif
def sieve_of_eratosthenes(n, count):
    sieve_size =n

    # is_prime = [True] * (sieve_size + 1)
    is_prime = []
    for i in range(sieve_size + 1):
        is_prime.append(True)

   

    is_prime[0] = is_prime[1] = False
    count += 2
    
    for i in range(2, int(sieve_size ** 0.5) + 1):
        if is_prime[i]:
            for j in range(i * i, sieve_size + 1, i):
                is_prime[j] = False
                count += 1

    count += sieve_size
    primes = [i for i in range(sieve_size + 1) if is_prime[i]]
    return primes, count
# Подушнить https://godbolt.org/
# Подушнить https://godbolt.org/

def factorize(n):
    count = 0
    factors = []

    primes, count = sieve_of_eratosthenes(int(n ** 0.5), count)
    for prime in primes:
        while n % prime == 0:
            factors.append(prime)
            n //= prime # подушнить
            count += 2
    if n > 1:
        factors.append(n)
    count += 1

    return factors, count

import time

start_time = time.time()
size = 20
data = sympy.randprime(2**(size-4), 2**size) * sympy.randprime(2**(size-4), 2**(size))


# result = factorize(data)
# sympy.factorint(data)
# end_time = time.time()
# print(f"Data: {data}")
# print(f"Result: {result[0]}")
# print(f"Count: {result[1]}")
# print(f"Execution time: {end_time - start_time:.4f} seconds")
# a = result[1]*1_000/(end_time - start_time)
# print(f"GHZ (not real): {a}")
# print("+++++++++++++")





start_time = time.time()
result = sympy.factorint(data)
end_time = time.time()

print(f"Data: {data}")
print(f"Result: {result}")
print(f"Execution time: {end_time - start_time:.4f} seconds")

#https://ru.wikipedia.org/wiki/%D0%A4%D0%B0%D0%BA%D1%82%D0%BE%D1%80%D0%B8%D0%B7%D0%B0%D1%86%D0%B8%D1%8F_%D1%86%D0%B5%D0%BB%D1%8B%D1%85_%D1%87%D0%B8%D1%81%D0%B5%D0%BB#%D0%9C%D0%B5%D1%82%D0%BE%D0%B4_%D0%BA%D0%B2%D0%B0%D0%B4%D1%80%D0%B0%D1%82%D0%B8%D1%87%D0%BD%D0%BE%D0%B3%D0%BE_%D1%80%D0%B5%D1%88%D0%B5%D1%82%D0%B0



print("========")
print("========")
print("========")
print("========")
print("========")
#  4 * 10^10

size2 = 2048
data2 = sympy.randprime(2**(size2-10), 2**size2) * sympy.randprime(2**(size2-10), 2**(size2))
print(data2)
deltaTime = end_time - start_time
print(data2*(data/deltaTime))
expectedTime=data2*(data/deltaTime)
print(f"Exprected time: {expectedTime:.4f} seconds")
print(f"Execution time: {(expectedTime) / 31_536_000:.10f} years")
print(f"Execution time: {(expectedTime) / 31_536_000:.10f} years")
s = f"{(expectedTime) / 31_536_000:.10f}"
print(len(s))