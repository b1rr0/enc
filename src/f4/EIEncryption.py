import random
import sympy

def generate_prime(bits):
    return sympy.randprime(2**(bits-1), 2**bits)

def gcd(a, b):
    while b:
        a, b = b, a % b
    return a

def mod_inverse(e, phi):
    return pow(e, -1, phi)

def generate_keys(bits):
    p = generate_prime(bits)
    q = generate_prime(bits)
    n = p * q
    phi = (p - 1) * (q - 1)

    e = 0 # default
    while gcd(e, phi) != 1:
        e = random.randrange(2, phi)

    d = mod_inverse(e, phi)    # e×d ≡ 1(mod phi)

    return (e, n), (d, n)

def encrypt(message, public_key):
    e, n = public_key
    return [pow(ord(char), e, n) for char in message]

def decrypt(ciphertext, private_key):
    d, n = private_key
    return ''.join(chr(pow(char, d, n)) for char in ciphertext)



public_key, private_key = generate_keys(70)
print("Public Key:", public_key)
print("Private Key:", private_key)

message = "Hello, RSA!"
ciphertext = encrypt(message, public_key)
print("Ciphertext:", ciphertext)

decrypted_message = decrypt(ciphertext, private_key)
print("Decrypted Message:", decrypted_message)

n = public_key[1]
#print("Factorization of n:", sympy.factorint(n))



def recover_private_key(n, e):
    factors = sympy.factorint(n) # https://github.com/sympy/sympy/blob/28ce606205d2d7544b13b7c74a275fb8a900087c/sympy/ntheory/factor_.py#L1220
    if len(factors) != 2:
        raise ValueError("Не удалось найти ровно два простых множителя")

    p, q = list(factors.keys()) 
    phi = (p - 1) * (q - 1) 
    d = pow(e, -1, phi)  
    return d, n

n = public_key[1]
e = public_key[0]
d, n = recover_private_key(n, e)

print("Recovered Private Key:", (d, n))

decrypted_message = decrypt(ciphertext, (d, n))
print("Decrypted Message with Recovered Key:", decrypted_message)


# print("-------")
# print(2**512)
# print(sympy.sqrt(2**512))
#
# print("------=")
#
# print(2**2048)
# print("_____")
# print(sympy.sqrt(2**2048))


