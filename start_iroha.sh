#!/usr/bin/env bash
docker rm iroha
docker run -it --name iroha \
-p 50051:50051 \
-v $(pwd)/src/main/resources/iroha:/opt/iroha_data \
-v blockstore:/tmp/block_store \
--network=iroha-network \
-e KEY=node0 \
-v $(pwd)/src/main/resources/iroha/entrypoint.sh:/entrypoint.sh \
hyperledger/iroha:1.0.0_beta-4