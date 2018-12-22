package jp.co.soramitsu.iroha_workshop_middleware.utils

import jp.co.soramitsu.crypto.ed25519.Ed25519Sha3
import java.io.IOException
import java.nio.file.FileSystems
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import java.security.KeyPair
import javax.xml.bind.DatatypeConverter

class KeysHelper {
    companion object {
        fun getKeys(userId: String): KeyPair {
            val pubKey = Ed25519Sha3.publicKeyFromBytes(readKeyFromFile("src/main/resources/iroha/$userId.pub"))
            val privKey = Ed25519Sha3.privateKeyFromBytes(readKeyFromFile("src/main/resources/iroha/$userId.priv"))
            return KeyPair(pubKey, privKey)
        }

        fun hexStringToByteArray(s: String): ByteArray {
            val len = s.length
            val data = ByteArray(len / 2)
            var i = 0
            while (i < len) {
                data[i / 2] = ((Character.digit(s[i], 16) shl 4) + Character.digit(s[i + 1], 16)).toByte()
                i += 2
            }
            return data
        }

        fun readKeyFromFile(path: String): ByteArray {
            try {
                return hexStringToByteArray(Files.readAllLines(Paths.get(path))[0])
            } catch (e: IOException) {
                throw Exception("Unable to read key files.\n $e")
            }
        }

        fun saveKeyPairToFile(keypair: KeyPair, userId: String) {
            val pathPub = FileSystems.getDefault().getPath("src/main/resources/iroha/$userId.pub")
            val pathPriv = FileSystems.getDefault().getPath("src/main/resources/iroha/$userId.priv")

            Files.write(pathPub,
                    DatatypeConverter.printHexBinary(keypair.public.encoded).toByteArray(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.WRITE,
                    StandardOpenOption.TRUNCATE_EXISTING)
            Files.write(pathPriv,
                    DatatypeConverter.printHexBinary(keypair.private.encoded).toByteArray(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.WRITE,
                    StandardOpenOption.TRUNCATE_EXISTING)
        }
    }

}