/**
 * 遍历树返回节点
 * @param tree
 * @param fn
 */
export function traverseTree(tree, fn) {

    if (!tree || typeof (fn) != 'function')
        return

    for (let node of tree) {
        fn(node)
        traverseTree(node.children, fn)
    }
}

